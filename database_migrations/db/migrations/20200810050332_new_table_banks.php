<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableBanks extends AbstractMigration
{

    public function change(): void
    {
        $shopBanks = $this->table('shop_banks', ['signed' => false]);
        $shopBanks->addColumn('shop_id', 'biginteger', ['signed' => false])
            ->addColumn('bank', 'string', ['limit' => 52])
            ->addColumn('account_no', 'string', ['limit' => 52])
            ->addColumn('iban', 'string', ['limit' => 52])
            ->addColumn('swift_code', 'string', ['limit' => 10])
            ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
            ->create();
    }
}
