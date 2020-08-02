<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class NewTableLanguages extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        $languages = $this->table('languages', ['signed' => false]);
        $languages->addColumn('title', 'string', ['limit' => 55])
            ->addColumn('code', 'string', ['limit' => 5])
            ->addIndex(['code'], ['unique' => true])
            ->create();
    }
}
